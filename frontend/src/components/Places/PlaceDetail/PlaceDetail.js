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
import { NotificiationService } from "../../../service/NotificiationService";

class PlaceDetail extends Component {
  state = { isCommeting: false };

  componentDidMount() {
    const { getPlace, match } = this.props;
    getPlace(match.params.id);
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
          <div className="col-md-12 d-flex flex-wrap align-items-center justify-content-start mt-2">
            {/* {comment.content} */}
            {/* {comment.text} */}
          </div>
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
      user
    } = this.props;
    console.log("TCL: PlaceDetail -> render -> user", user);
    const placeId = match.params.id;
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
                  <div style={{ position: "absolute", top: "5px", right: 0 }}>
                    <button
                      className="btn btn-danger"
                      onClick={() => {
                        NotificiationService.removeSubscription(
                          user.id,
                          placeId
                        );
                      }}
                    >
                      Unsubscribe
                    </button>
                    <button
                      className="btn btn-success"
                      onClick={() => {
                        NotificiationService.addSubscripiton(user.id, placeId);
                      }}
                    >
                      Subscribe
                    </button>
                  </div>
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
