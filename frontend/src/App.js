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
import Loader from "./common/Loader/Loader";
import Header from "./components/Header/Header";
import { authAction } from "./store/actions";
import "./App.css";

class App extends Component {
  componentDidMount() {
    const { isLogged } = this.props;
    isLogged();
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
        <Router>
          {isAuthenticated && <Header />}
          <div className="container">
            <main>
              {(!loading && (
                <Switch>
                  {routes.map(route => (
                    <Route
                      key={route.path}
                      exect
                      path={route.path}
                      component={route.component}
                    />
                  ))}
                  {redirect}
                </Switch>
              )) || <Loader />}
            </main>
          </div>
        </Router>
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
const mapDispatchToProps = {
  isLogged: authAction.isLogged
};
export default connect(
  mapStateToProps,
  mapDispatchToProps
)(App);
