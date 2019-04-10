import React, { Component } from "react";
import { connect } from "react-redux";
import { withRouter } from "react-router-dom";
import { placeAction } from "../../../store/actions";
import Card from "../../../common/Card/Card";
import Loader from "../../../common/Loader/Loader";
import CategoryBadge from "../../../common/CategoryBadge/CategoryBadge";
import { RATE_SCALE } from "../../../constans";
import Collection from "../../../common/Collection/Collection";

class PlaceDetail extends Component {
  state = {};

  componentDidMount() {
    const { getPlace, match } = this.props;
    getPlace(match.params.id);
  }

  mapCommentToRow(comment) {
    return (
      <div key={comment.id} className="places--row">
        <div className="row w-100">
          <div className="col-md-4 d-flex justify-content-start">
            <div className="font-weight-bold">{comment.user.name}</div>
          </div>
          <div className="absolute-center">
            <span className="badge badge-secondary badge-pill">
              {`${comment.rate.toFixed(1)} / ${RATE_SCALE}`}
            </span>
          </div>
          <div className="col-md-12 d-flex flex-wrap align-items-center justify-content-start mt-2">
            {comment.content}
          </div>
        </div>
      </div>
    );
  }

  render() {
    const { loading, selected = {} } = this.props;
    let address;
    let isCommerce;
    if (selected) {
      address = selected.address;
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
                    <div>{address.line}</div>
                    <div>{address.city}</div>
                    <div>{address.postalCode}</div>
                    <div>{address.country}</div>
                  </div>
                  <div className="d-flex flex-column align-items-start justify-content-start col-md-4 flex-wrap">
                    <div className="font-weight-bold"> Kontakt Details </div>
                    <div>{selected.owner.name}</div>
                    <div>{selected.email}</div>
                    <div>{selected.phone}</div>
                  </div>
                  <div className="d-flex flex-column align-items-start justify-content-start col-md-4 flex-wrap">
                    <div className="font-weight-bold"> Description </div>
                    <div className="text-left">{selected.description}</div>
                  </div>
                </div>
                <div className="divider" />
                <div className="row">
                  <div className="col-md-12">
                    <div className="font-weight-bold text-left">Comments:</div>
                  </div>
                  <div className="col-md-12">
                    <Collection
                      list={selected.comments.map(el => ({
                        id: el.id,
                        content: this.mapCommentToRow(el)
                      }))}
                    />
                  </div>
                </div>
              </div>
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
    selected: state.place.selected
  };
};

const mapDispatchToProps = {
  getPlace: placeAction.getPlace
};

export default withRouter(
  connect(
    mapStateToProps,
    mapDispatchToProps
  )(PlaceDetail)
);
