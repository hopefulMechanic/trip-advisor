import React, { Component } from "react";
import {
  BrowserRouter as Router,
  Route,
  Switch,
  Redirect
} from "react-router-dom";

import { connect } from "react-redux";
import LoginPage from "./components/LoginPage/LoginPage";
import Dashboard from "./components/Dashboard/Dashboard";
import RegisterForm from "./components/RegisterForm/RegisterForm";
import { LoginService } from "./service/LoginService";
import Loader from "./common/Loader/Loader";
import "./App.css";

class App extends Component {
  componentDidMount() {
    this.isUserLoggedin();
  }

  isUserLoggedin() {
    // LoginService.isUserAutheticated().then(res =>
    //   this.setState({ isAuthenticated: res, loading: false })
    // );
  }

  render() {
    const { isAuthenticated, loading } = this.props;
    const redirect = isAuthenticated ? (
      <Redirect to="/dashboard" />
    ) : (
      <Redirect to="/login" />
    );
    let routes;
    if (isAuthenticated) {
      routes = [{ component: Dashboard, path: "/dashboard" }];
    } else {
      routes = [
        { component: LoginPage, path: "/login" },
        { component: RegisterForm, path: "/register" }
      ];
    }

    return (
      <div className="App">
        <div className="container">
          <Router>
            <main>
              {(!loading && (
                <Switch>
                  {routes.map(route => (
                    <Route
                      exect
                      path={route.path}
                      component={route.component}
                    />
                  ))}
                  {redirect}
                </Switch>
              )) || <Loader />}
            </main>
          </Router>
        </div>
      </div>
    );
  }
}
const mapStateToProps = state => {
  return {
    isAuthenticated: state.auth.isAuthenticated,
    loading: state.auth.loading
  };
};
export default connect(mapStateToProps)(App);
