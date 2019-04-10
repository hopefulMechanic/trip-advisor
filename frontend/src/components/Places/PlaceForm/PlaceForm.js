import React, { Component } from "react";
import Card from "../../../common/Card/Card";
import set from "lodash-es/set";
import { CATEGORIES_NAMES } from "../../../constans";
import CategoryBadge from "../../../common/CategoryBadge/CategoryBadge";

class PlaceForm extends Component {
  state = {
    name: "",
    email: "",
    phone: "",
    address: {
      line: "",
      city: "",
      postalCode: "",
      country: ""
    },
    categories: [],
    entranceFee: 0,
    type: "normal"
  };

  submitHandler() {
    console.log(this.state);
    // const { register } = this.props;
    // const { username, password, confirmPassword, type } = this.state;
    // if (password === confirmPassword) {
    //   const user = { username, password, type };
    //   register(user);
    // } else {
    //   this.setState({ error: true });
    // }
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
      address: { line, city, postalCode, country },
      entranceFee,
      email,
      phone,
      categories
    } = this.state;
    const categoriesKeys = Object.keys(CATEGORIES_NAMES);

    const isValid = true;
    return (
      <Card header="Create Place">
        <form
          onSubmit={event => {
            this.submitHandler();
            event.preventDefault();
          }}
        >
          <div className="form-row">
            <div className="col-md-12 mb-2">
              <div className="form-group">
                <div className="custom-control custom-switch">
                  <input
                    type="checkbox"
                    className="custom-control-input"
                    id="accountType"
                    onClick={event => {
                      this.handleChange(
                        type === "normal" ? "premium" : "normal",
                        "type"
                      );
                    }}
                  />
                  <label className="custom-control-label" htmlFor="accountType">
                    Commercial
                  </label>
                </div>
              </div>
            </div>
          </div>
          <div className="divider" />
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
                <label htmlFor="address.line">Address</label>
                <input
                  onChange={event =>
                    this.handleChange(event.target.value, "address.line")
                  }
                  value={line}
                  type="text"
                  className="form-control"
                  id="address.line"
                />
              </div>
            </div>
          </div>
          <div className="form-row">
            <div className="col-md-6 mb-2">
              <div className="form-group">
                <label htmlFor="address.city">City</label>
                <input
                  type="text"
                  className="form-control"
                  id="address.city"
                  value={city}
                  onChange={event =>
                    this.handleChange(event.target.value, "address.city")
                  }
                />
              </div>
            </div>

            <div className="col-md-3 mb-2">
              <div className="form-group">
                <label htmlFor="address.country">Country</label>
                <input
                  type="text"
                  className="form-control"
                  id="address.country"
                  value={country}
                  onChange={event =>
                    this.handleChange(event.target.value, "address.country")
                  }
                />
              </div>
            </div>
            <div className="col-md-3 mb-2">
              <div className="form-group">
                <label htmlFor="address.postalCode">Postal Code</label>
                <input
                  type="text"
                  className="form-control"
                  id="address.postalCode"
                  value={postalCode}
                  onChange={event =>
                    this.handleChange(event.target.value, "address.postalCode")
                  }
                />
              </div>
            </div>
          </div>
          <div className="form-row">
            <div className="col-md-6 mb-2">
              {categories.length > 0 && (
                <div>
                  Selected:{" "}
                  {categories.map(category => (
                    <CategoryBadge key={category} category={category} />
                  ))}
                </div>
              )}
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
              </div>
            </div>
            {type === "premium" && (
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
            Submit
          </button>
        </form>
      </Card>
    );
  }
}

export default PlaceForm;