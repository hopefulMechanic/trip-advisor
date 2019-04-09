import React, { Component } from "react";
import { Link } from "react-router-dom";
import { connect } from "react-redux";
import { authAction } from "../../store/actions";
import Modal from "../../common/Modal/Modal";
import LoginForm from "../LoginForm/LoginForm";
import RegisterForm from "../RegisterForm/RegisterForm";

class Header extends Component {
  state = {
    register: false
  };

  componentDidMount() {
    const { isLogged } = this.props;
    isLogged();
  }

  render() {
    const { register } = this.state;
    const { user, logout } = this.props;
    console.log("TCL: Header -> render -> user", user);
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
              <Link className="navbar-brand" to="/places">
                Home
              </Link>
            </li>
          </ul>
          {(user && (
            <div className="dropdown">
              <button
                className="btn dropdown-toggle btn__no-border"
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
          )) || (
            <button
              className="btn btn__no-border"
              data-toggle="modal"
              data-target="#loginModal"
            >
              Login
            </button>
          )}
        </div>
        {!user && (
          <Modal
            id="loginModal"
            title={!register ? "Login" : "Register"}
            footer={
              <div className="d-flex justify-content-center w-100">
                <button
                  type="button"
                  class="btn btn-link"
                  onClick={() =>
                    this.setState({ register: !register ? true : false })
                  }
                >
                  {!register ? "Register account" : "Go to Login"}
                </button>
              </div>
            }
            content={!register ? <LoginForm /> : <RegisterForm />}
          />
        )}
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
  logout: authAction.logout,
  isLogged: authAction.isLogged
};

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Header);
