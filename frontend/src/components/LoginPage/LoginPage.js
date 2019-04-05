import React, { Component } from "react";
import { connect } from "react-redux";
import { Link } from "react-router-dom";
import { authAction } from "../../store/actions";
import Card from "../../common/Card/Card";
class LoginPage extends Component {
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
      <Card
        header={"Login"}
        footer={<Link to="/register">Register account</Link>}
      >
        <form
          style={{ width: "400px" }}
          onSubmit={event => {
            this.submitHandler();
            event.preventDefault();
          }}
        >
          <div className="form-group">
            {wrongCredetials && (
              <p className="text-danger"> Wrong Credetials</p>
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
          <button
            type="button"
            className="btn btn-primary"
            disabled={username === "" || password === ""}
            onClick={() => this.submitHandler()}
          >
            Submit
          </button>
        </form>
      </Card>
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
)(LoginPage);
