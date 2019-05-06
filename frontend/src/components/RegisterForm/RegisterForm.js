import React, { Component } from "react";
import { connect } from "react-redux";
import { authAction } from "../../store/actions";
import { USER_TYPES } from "../../constans";

class RegisterForm extends Component {
  state = {
    nickname: "",
    password: "",
    email: "",
    lastName: "",
    firstName: "",
    confirmPassword: "",
    role: USER_TYPES.regular,
    passwordError: false
  };

  submitHandler() {
    const { register } = this.props;
    const {
      nickname,
      password,
      email,
      lastName,
      firstName,
      confirmPassword,
      role
    } = this.state;
    if (password === confirmPassword) {
      const user = { nickname, password, role, email, lastName, firstName };
      register(user);
    } else {
      this.setState({ passwordError: true });
    }
  }

  handleChange(value, id) {
    this.setState({ [id]: value, passwordError: false });
  }
  render() {
    const {
      nickname,
      password,
      confirmPassword,
      email,
      lastName,
      firstName,
      passwordError
    } = this.state;

    const isDisabled =
      nickname === "" ||
      password === "" ||
      email === "" ||
      lastName === "" ||
      firstName === "";

    return (
      <form
        onSubmit={event => {
          this.submitHandler();
          event.preventDefault();
        }}
      >
        <div className="form-group">
          {passwordError && (
            <p className="text-danger"> Passwords doesnt match</p>
          )}
        </div>
        <div className="form-group">
          <label htmlFor="nickname">Username</label>
          <input
            onChange={event =>
              this.handleChange(event.target.value, "nickname")
            }
            value={nickname}
            type="text"
            className="form-control"
            id="nickname"
          />
        </div>
        <div className="form-group">
          <label htmlFor="firstName">First Name</label>
          <input
            onChange={event =>
              this.handleChange(event.target.value, "firstName")
            }
            value={firstName}
            type="text"
            className="form-control"
            id="firstName"
          />
        </div>
        <div className="form-group">
          <label htmlFor="lastName">Last Name</label>
          <input
            onChange={event =>
              this.handleChange(event.target.value, "lastName")
            }
            value={lastName}
            type="text"
            className="form-control"
            id="lastName"
          />
        </div>
        <div className="form-group">
          <label htmlFor="email">Email</label>
          <input
            onChange={event => this.handleChange(event.target.value, "email")}
            value={email}
            type="text"
            className="form-control"
            id="email"
          />
        </div>
        <div className="form-group">
          <label htmlFor="password">Password</label>
          <input
            onChange={event =>
              this.handleChange(event.target.value, "password")
            }
            value={password}
            type="password"
            className="form-control"
            id="password"
          />
        </div>
        <div className="form-group">
          <label htmlFor="confirmPassword">Confirm Password</label>
          <input
            onChange={event =>
              this.handleChange(event.target.value, "confirmPassword")
            }
            value={confirmPassword}
            type="password"
            className="form-control"
            id="confirmPassword"
          />
        </div>
        <div className="form-group">
          <div className="custom-control custom-switch">
            <input
              type="checkbox"
              className="custom-control-input"
              id="accountType"
              onClick={event => {
                const { role } = this.state;
                this.handleChange(
                  role === USER_TYPES.regular
                    ? USER_TYPES.commercial
                    : USER_TYPES.regular,
                  "role"
                );
              }}
            />
            <label className="custom-control-label" htmlFor="accountType">
              Commercial
            </label>
          </div>
        </div>
        <button
          type="button"
          className="btn btn-primary"
          disabled={isDisabled}
          onClick={() => this.submitHandler()}
        >
          Submit
        </button>
      </form>
    );
  }
}
const mapDispatchToProps = {
  register: authAction.register
};

export default connect(
  null,
  mapDispatchToProps
)(RegisterForm);
