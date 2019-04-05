import React, { Component } from "react";
import { Link } from "react-router-dom";
import { connect } from "react-redux";
import { authAction } from "../../store/actions";

class Header extends Component {
  state = {};

  render() {
    const { user, logout } = this.props;
    return (
      <nav className="navbar navbar-expand-lg navbar-dark bg-dark">
        <div className="navbar-brand">Trip Advisor</div>
        <button
          className="navbar-toggler"
          type="button"
          data-toggle="collapse"
          data-target="#navbarSupportedContent"
          aria-controls="navbarSupportedContent"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <span className="navbar-toggler-icon" />
        </button>

        <div className="collapse navbar-collapse" id="navbarSupportedContent">
          <ul className="navbar-nav mr-auto">
            <li className="nav-item active">
              <Link className="navbar-brand" to="/dashboard">
                Home
              </Link>
            </li>
          </ul>
          <div className="dropdown">
            <button
              className="btn dropdown-toggle"
              style={{ background: "none", border: "none", color: "white" }}
              type="button"
              id="userProfile"
              data-toggle="dropdown"
              aria-haspopup="true"
              aria-expanded="false"
            >
              {user.name}
            </button>
            <div
              className="dropdown-menu dropdown-menu-right"
              aria-labelledby="userProfile"
            >
              <button
                className="dropdown-item"
                type="button"
                onClick={() => logout()}
              >
                logout
              </button>
            </div>
          </div>
        </div>
      </nav>
    );
  }
}

const mapStateToProps = state => {
  return {
    user: state.auth.user,
    loading: state.auth.loading
  };
};

const mapDispatchToProps = {
  logout: authAction.logout
};

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Header);
