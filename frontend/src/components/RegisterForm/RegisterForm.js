import React, { Component } from "react";
import { connect } from "react-redux";
import { authAction } from "../../store/actions";

class RegisterForm extends Component {
  state = {
    username: "",
    password: "",
    confirmPassword: "",
    type: "normal",
    passwordError: false
  };

  submitHandler() {
    const { register } = this.props;
    const { username, password, confirmPassword, type } = this.state;
    if (password === confirmPassword) {
      const user = { username, password, type };
      register(user);
    } else {
      this.setState({ passwordError: true });
    }
  }

  handleChange(value, id) {
    this.setState({ [id]: value, passwordError: false });
  }
  render() {
    const { username, password, confirmPassword, passwordError } = this.state;

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
          <label htmlFor="username">Username</label>
          <input
            onChange={event =>
              this.handleChange(event.target.value, "username")
            }
            value={username}
            type="text"
            className="form-control"
            id="username"
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
                const { type } = this.state;
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
        <button
          type="button"
          className="btn btn-primary"
          disabled={username === "" || password === ""}
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
