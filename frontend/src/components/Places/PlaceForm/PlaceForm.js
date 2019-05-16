import React, { Component } from "react";
import set from "lodash-es/set";
import { connect } from "react-redux";
import { withRouter } from "react-router-dom";
import Card from "../../../common/Card/Card";
import omit from "lodash-es/omit";
import { CATEGORIES_NAMES, PLACE_TYPES, USER_TYPES } from "../../../constans";
import CategoryBadge from "../../../common/CategoryBadge/CategoryBadge";
import { placeAction } from "../../../store/actions";

import Loader from "../../../common/Loader/Loader";

class PlaceForm extends Component {
  state = {
    name: "",
    description: "",
    addressLine: "",
    city: "",
    postalCode: "",
    country: "",
    entranceFee: 0,
    email: "",
    phone: "",
    categories: [],
    type: PLACE_TYPES.regular,
    loaded: false
  };

  componentDidMount() {
    const { getPlace, match } = this.props;
    if (match.params.id) {
      getPlace(match.params.id);
    }
  }

  static getDerivedStateFromProps(props, state) {
    if (props.match.params.id) {
      if (props.selected && !state.loaded) {
        const {
          selected: {
            name,
            description,
            addressLine,
            city,
            postalCode,
            country,
            entranceFee,
            email,
            phone,
            categories
          }
        } = props;
        return {
          name,
          description,
          addressLine,
          city,
          postalCode,
          country,
          entranceFee,
          email,
          phone,
          categories,
          type:
            entranceFee !== 0 ? PLACE_TYPES.commercial : PLACE_TYPES.regular,
          loaded: true
        };
      }
    } else {
      return { loaded: true };
    }

    return null;
  }

  submitHandler() {
    const { addPlace, history, user, match, selected } = this.props;
    if (match.params.id) {
      addPlace(
        {
          ...selected,
          ...omit(this.state, "loaded"),
          createdBy: selected.createdBy.id
        },
        history
      );
    } else {
      addPlace({ ...omit(this.state, "loaded"), createdBy: user.id }, history);
    }
  }

  handleChange(value, id) {
    const stateCopy = { ...this.state };
    set(stateCopy, id, value);
    if (id === "type") {
      stateCopy.entranceFee = 0;
    }
    this.setState(stateCopy);
  }

  render() {
    const {
      name,
      type,
      addressLine,
      city,
      postalCode,
      country,
      entranceFee,
      description,
      email,
      phone,
      categories,
      loaded
    } = this.state;
    const { user, loading } = this.props;
    const categoriesKeys = Object.keys(CATEGORIES_NAMES);
    const isCommercial = user && user.role === USER_TYPES.commercial;
    const isValid =
      name !== "" &&
      addressLine !== "" &&
      city !== "" &&
      postalCode !== "" &&
      country !== "" &&
      email !== "" &&
      categories.length > 0 &&
      phone !== "";
    return (
      <Card header="Create Place">
        <form
          onSubmit={event => {
            this.submitHandler();
            event.preventDefault();
          }}
        >
          {isCommercial && (
            <div className="form-row">
              <div className="col-md-12 mb-2">
                <div className="form-group">
                  <div className="custom-control custom-switch">
                    {loaded && (
                      <input
                        type="checkbox"
                        defaultChecked={type === PLACE_TYPES.commercial}
                        className="custom-control-input"
                        id="accountType"
                        onClick={event => {
                          this.handleChange(
                            type === PLACE_TYPES.regular
                              ? PLACE_TYPES.commercial
                              : PLACE_TYPES.regular,
                            "type"
                          );
                        }}
                      />
                    )}
                    <label
                      className="custom-control-label"
                      htmlFor="accountType"
                    >
                      Commercial
                    </label>
                  </div>
                </div>
              </div>
            </div>
          )}

          {isCommercial && <div className="divider" />}

          <div className="form-row">
            <div className="col-md-12 mb-2">
              <label className="float-left font-weight-bold">
                Place Details
              </label>
            </div>
          </div>
          <div className="form-row">
            <div className="col-md-6  mb-2">
              <div className="form-group">
                <label htmlFor="name">Name</label>
                <input
                  onChange={event =>
                    this.handleChange(event.target.value, "name")
                  }
                  value={name}
                  type="text"
                  className="form-control"
                  id="name"
                />
              </div>
            </div>
            <div className="col-md-6  mb-2">
              <div className="form-group">
                <label htmlFor="addressLine">Address</label>
                <input
                  onChange={event =>
                    this.handleChange(event.target.value, "addressLine")
                  }
                  value={addressLine}
                  type="text"
                  className="form-control"
                  id="addressLine"
                />
              </div>
            </div>
          </div>
          <div className="form-row">
            <div className="col-md-6 mb-2">
              <div className="form-group">
                <label htmlFor="city">City</label>
                <input
                  type="text"
                  className="form-control"
                  id="city"
                  value={city}
                  onChange={event =>
                    this.handleChange(event.target.value, "city")
                  }
                />
              </div>
            </div>

            <div className="col-md-3 mb-2">
              <div className="form-group">
                <label htmlFor="country">Country</label>
                <input
                  type="text"
                  className="form-control"
                  id="country"
                  value={country}
                  onChange={event =>
                    this.handleChange(event.target.value, "country")
                  }
                />
              </div>
            </div>
            <div className="col-md-3 mb-2">
              <div className="form-group">
                <label htmlFor="postalCode">Postal Code</label>
                <input
                  type="text"
                  className="form-control"
                  id="postalCode"
                  value={postalCode}
                  onChange={event =>
                    this.handleChange(event.target.value, "postalCode")
                  }
                />
              </div>
            </div>
          </div>
          <div className="form-row">
            <div className="col-md-6 mb-2">
              <div className="form-group">
                <label htmlFor="categories">Categories</label>
                <select multiple className="form-control" id="categories">
                  {categoriesKeys.map(key => (
                    <option
                      key={key}
                      className={categories.includes(key) ? "selected" : ""}
                      onDoubleClick={() => {
                        const index = categories.indexOf(key);
                        const categoriesCopy = [...categories];
                        if (index >= 0) {
                          categoriesCopy.splice(index, 1);
                        } else {
                          categoriesCopy.push(key);
                        }
                        this.handleChange(categoriesCopy, "categories");
                      }}
                    >
                      {CATEGORIES_NAMES[key]}
                    </option>
                  ))}
                </select>
                {categories.length > 0 && (
                  <div>
                    Selected:{" "}
                    {categories.map(category => (
                      <CategoryBadge key={category} category={category} />
                    ))}
                  </div>
                )}
              </div>
            </div>
            <div className="col-md-6 mb-2">
              <div className="form-group">
                <label htmlFor="description">Description</label>
                <textarea
                  id="description"
                  className="form-control"
                  value={description}
                  onChange={event =>
                    this.handleChange(event.target.value, "description")
                  }
                />
              </div>
            </div>
            {type === PLACE_TYPES.commercial && (
              <div className="col-md-6 mb-2">
                <div className="form-group">
                  <label htmlFor="entranceFee">Entrance Fee</label>
                  <input
                    onChange={event =>
                      this.handleChange(+event.target.value, "entranceFee")
                    }
                    onBlur={() =>
                      this.handleChange(entranceFee.toFixed(2), "entranceFee")
                    }
                    value={entranceFee}
                    type="number"
                    className="form-control"
                    id="entranceFee"
                  />
                </div>
              </div>
            )}
          </div>

          <div className="divider" />
          <div className="form-row">
            <div className="col-md-12 mb-2">
              <label className="float-left font-weight-bold">
                Contact Details
              </label>
            </div>
          </div>
          <div className="form-row">
            <div className="col-md-6 mb-2">
              <div className="form-group">
                <label htmlFor="email">Email</label>
                <input
                  type="email"
                  className="form-control"
                  id="email"
                  value={email}
                  onChange={event =>
                    this.handleChange(event.target.value, "email")
                  }
                />
              </div>
            </div>
            <div className="col-md-6 mb-2">
              <div className="form-group">
                <label htmlFor="phone">Phone</label>
                <input
                  type="tel"
                  className="form-control"
                  id="phone"
                  value={phone}
                  onChange={event =>
                    this.handleChange(event.target.value, "phone")
                  }
                />
              </div>
            </div>
          </div>
          <div className="divider" />
          <button
            type="button"
            className="btn btn-primary"
            disabled={!isValid}
            onClick={() => this.submitHandler()}
          >
            {(loading && <Loader />) || "Save"}
          </button>
        </form>
      </Card>
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
  addPlace: placeAction.addPlace,
  getPlace: placeAction.getPlace
};

export default withRouter(
  connect(
    mapStateToProps,
    mapDispatchToProps
  )(PlaceForm)
);
