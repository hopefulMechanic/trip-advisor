import React, { Component } from "react";
import { connect } from "react-redux";
import { withRouter } from "react-router-dom";
import { placeAction } from "../../../store/actions";
import Card from "../../../common/Card/Card";
import Loader from "../../../common/Loader/Loader";
import CategoryBadge from "../../../common/CategoryBadge/CategoryBadge";
import Collection from "../../../common/Collection/Collection";
import CommentForm from "./CommentForm/CommentForm";
import Rating from "react-rating";
import { NotificationService } from "../../../service/NotificationService";

class PlaceDetail extends Component {
  state = { isCommeting: false, message: "", isSubscriber: null };

  componentDidMount() {
    const { getPlace, match } = this.props;
    getPlace(match.params.id);
  }

  componentDidUpdate() {
    const { match, user } = this.props;
    const { isSubscriber } = this.state;
    if (user != null && isSubscriber == null) {
      const placeId = match.params.id;
      NotificationService.checkIfIsObserver(placeId, user.id)
        .then(() => {
          this.setState({ isSubscriber: true });
        })
        .catch(() => {
          this.setState({ isSubscriber: false });
        });
    }
  }
  mapCommentToRow(comment) {
    const { user, deleteComment, match } = this.props;
    const placeId = match.params.id;
    return (
      <div key={comment.id} className="places--row">
        <div className="row w-100">
          <div className="col-md-3 d-flex justify-content-start flex-column">
            <div className="font-weight-bold">{comment.user.firstName}:</div>
            <div>{comment.modifyDate}</div>
          </div>
          <div className="col-md-6 d-flex flex-column justify-content-start">
            <div>{comment.text}</div>
            <Rating readonly initialRating={comment.score} stop={10} />
          </div>
          {user != null && user.id === comment.user.id && (
            <div className="absolute-center">
              <span
                className="badge badge-secondary badge-pill"
                onClick={() => {
                  deleteComment(comment.id, placeId);
                }}
              >
                usun
              </span>
            </div>
          )}
        </div>
      </div>
    );
  }

  render() {
    const {
      loading,
      selected,
      addingComment,
      match,
      addComment,
      user,
      history
    } = this.props;

    const { message, isSubscriber } = this.state;
    const placeId = match.params.id;
    const userOwnPlace =
      user != null &&
      (selected != null && selected.createdBy) &&
      selected.createdBy.id === user.id;
    let isCommerce;
    if (selected) {
      isCommerce = selected.entranceFee > 0;
    }
    return (
      <div className="place-detail">
        {(!loading && selected != null && (
          <div className="place-detail">
            <Card
              header={
                <span className="font-weight-bold">
                  {selected.name}
                  {
                    <span
                      className={`ml-2 font-weight-bold ${
                        isCommerce ? "text-danger" : "text-success"
                      }`}
                    >
                      {(isCommerce && `${selected.entranceFee}$`) || "Free"}
                    </span>
                  }
                  {user && (
                    <div
                      style={{ position: "absolute", top: "5px", right: "5px" }}
                    >
                      {userOwnPlace && (
                        <button
                          className="btn btn-primary"
                          onClick={() => {
                            history.push(`/places/${placeId}/edit`);
                          }}
                        >
                          Edit
                        </button>
                      )}
                      {isSubscriber === true && (
                        <button
                          className="btn btn-danger"
                          onClick={() => {
                            NotificationService.removeSubscription(
                              user.id,
                              placeId
                            ).then(() =>
                              this.setState({ isSubscriber: false })
                            );
                          }}
                        >
                          Unsubscribe
                        </button>
                      )}
                      {isSubscriber === false && (
                        <button
                          className="btn btn-success"
                          onClick={() => {
                            NotificationService.addSubscripiton(
                              user.id,
                              placeId
                            ).then(() => this.setState({ isSubscriber: true }));
                          }}
                        >
                          Subscribe
                        </button>
                      )}
                    </div>
                  )}
                </span>
              }
            >
              <div className="container-fluid">
                <div className="row">
                  <div className="d-flex justify-content-start align-items-start col-md-12 flex-wrap">
                    {selected.categories.map(category => (
                      <CategoryBadge key={category} category={category} />
                    ))}
                  </div>
                </div>
                <div className="row mt-2">
                  <div className="d-flex flex-column align-items-start justify-content-start col-md-4 flex-wrap">
                    <div className="font-weight-bold"> Address Details </div>
                    <div>{selected.addressLine}</div>
                    <div>{selected.city}</div>
                    <div>{selected.postalCode}</div>
                    <div>{selected.country}</div>
                  </div>
                  <div className="d-flex flex-column align-items-start justify-content-start col-md-4 flex-wrap">
                    <div className="font-weight-bold"> Kontakt Details </div>
                    <div>{selected.name}</div>
                    <div>{selected.email}</div>
                    <div>{selected.phone}</div>
                  </div>
                  <div className="d-flex flex-column align-items-start justify-content-start col-md-4 flex-wrap">
                    <div className="font-weight-bold"> Description </div>
                    <div className="text-left">{selected.description}</div>
                  </div>
                </div>
              </div>
            </Card>
            {userOwnPlace && isCommerce && (
              <Card header="Add Notification">
                <form
                  className="form-inline"
                  onSubmit={event => {
                    const { message } = this.state;
                    NotificationService.notifyObservers(placeId, message).then(
                      () => {
                        this.setState({ message: "" });
                      }
                    );
                    event.preventDefault();
                  }}
                >
                  <input
                    type="text"
                    value={message}
                    style={{ width: "100%" }}
                    className="form-control mb-2 mr-sm-2"
                    id="message"
                    placeholder="Message"
                    onChange={event =>
                      this.setState({ message: event.target.value })
                    }
                  />
                  <button
                    type="submit"
                    disabled={message === ""}
                    className="btn btn-secondary mb-2"
                  >
                    Notify Users
                  </button>
                </form>
              </Card>
            )}
            <Card header="Comments">
              {user != null && (
                <CommentForm
                  submitHanlder={comment => addComment(placeId, comment)}
                />
              )}
              {(!addingComment && (
                <Collection
                  list={selected.comments.map(el => ({
                    id: el.id,
                    content: this.mapCommentToRow(el)
                  }))}
                />
              )) || <Loader />}
            </Card>
          </div>
        )) || <Loader size="large" />}
      </div>
    );
  }
}

const mapStateToProps = state => {
  return {
    user: state.auth.user,
    loading: state.place.loading,
    selected: state.place.selected,
    addingComment: state.place.addingComment
  };
};

const mapDispatchToProps = {
  getPlace: placeAction.getPlace,
  addComment: placeAction.addComment,
  deleteComment: placeAction.deleteComment
};

export default withRouter(
  connect(
    mapStateToProps,
    mapDispatchToProps
  )(PlaceDetail)
);
