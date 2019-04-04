import React, { Component } from "react";
import Card from "../../common/Card/Card";

class RegisterForm extends Component {
  state = { username: "", password: "", confirmPassword: "" };

  submitHandler() {
    console.log(this.state);
  }

  handleChange(value, id) {
    this.setState({ [id]: value });
  }
  render() {
    const { username, password, confirmPassword } = this.state;

    return (
      <Card header={"Login"} class="test">
        <form
          style={{ width: "400px" }}
          onSubmit={event => {
            this.submitHandler();
            event.preventDefault();
          }}
        >
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
          <button
            type="button"
            className="btn btn-primary"
            onClick={() => this.submitHandler()}
          >
            Submit
          </button>
        </form>
      </Card>
    );
  }
}

export default RegisterForm;