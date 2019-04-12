import React, { Component } from "react";
import { connect } from "react-redux";
import { authAction } from "../../store/actions";
class LoginForm extends Component {
  state = { username: "", password: "" };

  submitHandler() {
    const { login } = this.props;
    login(this.state);
  }

  handleChange(value, id) {
    this.setState({ [id]: value });
  }

  render() {
    const { username, password } = this.state;
    const { wrongCredetials } = this.props;
    return (
      <form
        onSubmit={event => {
          this.submitHandler();
          event.preventDefault();
        }}
      >
        <div className="form-group">
          {wrongCredetials && <p className="text-danger"> Wrong Credetials</p>}
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

const mapStateToProps = state => ({
  wrongCredetials: state.auth.wrongCredetials
});

const mapDispatchToProps = {
  login: authAction.login
};

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(LoginForm);
