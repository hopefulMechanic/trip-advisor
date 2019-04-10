import React, { Component } from "react";
import { withRouter } from "react-router-dom";
import { connect } from "react-redux";
import Card from "../../common/Card/Card";
import Collection from "../../common/Collection/Collection";
import { placeAction } from "../../store/actions";
import Loader from "../../common/Loader/Loader";
import { RATE_SCALE } from "../../constans";
import "./Places.scss";
import CategoryBadge from "../../common/CategoryBadge/CategoryBadge";
class Places extends Component {
  state = {};

  componentDidMount() {
    const { getPlaces } = this.props;
    getPlaces();
  }

  mapPlaceToRow(el) {
    const rateSum = el.comments
      .map(el => el.rate)
      .reduce((prev, curr) => (prev += curr), 0);
    const rateAmout = el.comments.length;
    return (
      <div key={el.id} className="places--row">
        <div className="row w-100">
          <div className="places--tile col-md-4">
            <div className="font-weight-bold">Name:</div>
            <div className="text-left">{el.name}</div>
          </div>
          <div className="places--tile col-md-4">
            <div className="font-weight-bold">Description:</div>
            <div className="text-left">{el.description}</div>
          </div>
          <div className="places--tile col-md-4">
            <div className="font-weight-bold">Address:</div>
            <div className="text-left">
              {el.address.line} {el.address.city}
            </div>
          </div>
          <div className="absolute-center">
            <span className="badge badge-secondary badge-pill">
              {rateAmout > 0 ? `${rateSum.toFixed(1)} / ${RATE_SCALE}` : "-"}
            </span>
          </div>
          <div className="col-md-12 d-flex flex-wrap align-items-center justify-content-start mt-2">
            <div className="font-weight-bold mr-1">Categories:</div>
            {el.categories.map(category => (
              <CategoryBadge category={category} />
            ))}
          </div>
        </div>
      </div>
    );
  }

  render() {
    const { history, list, loading, user } = this.props;
    return (
      <div className="places">
        <Card header="Places List">
          {user != null && !loading && (
            <button
              type="button"
              className="btn btn-secondary"
              onClick={() => {
                history.push(`/places/new`);
              }}
            >
              New Place
            </button>
          )}
          <div className="divider" />
          {(!loading && (
            <Collection
              onDoubleClick={id => {
                history.push(`/places/${id}`);
              }}
              list={list.map(el => ({
                id: el.id,
                content: this.mapPlaceToRow(el)
              }))}
            />
          )) || <Loader size="large" />}
        </Card>
      </div>
    );
  }
}

const mapStateToProps = state => {
  return {
    user: state.auth.user,
    loading: state.place.loading,
    list: state.place.list
  };
};

const mapDispatchToProps = {
  getPlaces: placeAction.getPlaces
};

export default withRouter(
  connect(
    mapStateToProps,
    mapDispatchToProps
  )(Places)
);
