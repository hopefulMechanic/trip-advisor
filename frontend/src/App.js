import React, { Component } from "react";
import {
  BrowserRouter as Router,
  Route,
  Switch,
  Redirect
} from "react-router-dom";
import LoginPage from "./components/LoginPage/LoginPage";
import Dashboard from "./components/Dashboard/Dashboard";
import RegisterForm from "./components/RegisterForm/RegisterForm";
import { LoginService } from "./service/LoginService";
import Loader from "./common/Loader/Loader";

import "./App.css";

class App extends Component {
  state = {
    isAuthenticated: false,
    loading: true
  };

  componentDidMount() {
    this.isUserLoggedin();
  }

  isUserLoggedin() {
    LoginService.isUserAutheticated().then(
      res => {}
      // this.setState({ isAuthenticated: res, loading: false })
    );
  }

  render() {
    const { isAuthenticated, loading } = this.state;
    const redirect = isAuthenticated ? (
      <Redirect to="/dashboard" />
    ) : (
      <Redirect to="/login" />
    );
    return (
      <div className="App">
        <div className="container">
          <Router>
            <main>
              {(!loading && (
                <Switch>
                  <Route path="/login" component={LoginPage} />
                  <Route exect path="/dashboard" component={Dashboard} />
                  <Route exect path="/register" component={RegisterForm} />
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

export default App;
