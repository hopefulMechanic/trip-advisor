import React, { Component } from "react";
import { withRouter } from "react-router-dom";
import { connect } from "react-redux";
import Card from "../../common/Card/Card";
import Collection from "../../common/Collection/Collection";
import { placeAction } from "../../store/actions";
import Loader from "../../common/Loader/Loader";
import "./Places.scss";
import CategoryBadge from "../../common/CategoryBadge/CategoryBadge";
import debounce from "lodash-es/debounce";
class Places extends Component {
  state = {
    query: ""
  };
  filter;
  componentDidMount() {
    const { getPlaces } = this.props;
    getPlaces();
    this.fitler = debounce(getPlaces, 500);
  }

  mapPlaceToRow(el) {
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
              {el.addressLine} {el.city}
            </div>
          </div>
          <div className="absolute-center">
            <span className="badge badge-secondary badge-pill">
              {el.score < 0 ? "No Score" : `${el.score}/10`}
            </span>
          </div>
          <div className="col-md-12 d-flex flex-wrap align-items-center justify-content-start mt-2">
            <div className="font-weight-bold mr-1">Categories:</div>
            {el.categories.map(category => (
              <CategoryBadge
                key={`${el.name.trim()}-${category}`}
                category={category}
              />
            ))}
          </div>
        </div>
      </div>
    );
  }

  render() {
    const { history, list, loading, user } = this.props;
    const { query } = this.state;
    return (
      <div className="places">
        <Card header="Places List">
          {user != null && (
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
          {user != null && <div className="divider" />}
          <div className="form-group">
            <input
              onChange={event => {
                this.setState({ query: event.target.value });
                this.fitler(event.target.value);
              }}
              value={query}
              type="text"
              placeholder="Find Place"
              className="form-control"
              id="query"
            />
          </div>
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
