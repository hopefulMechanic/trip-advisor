import React, { Component } from "react";
import {
  BrowserRouter as Router,
  Route,
  Switch,
  Redirect
} from "react-router-dom";
import Dashboard from "./components/Dashboard/Dashboard";
import Header from "./components/Header/Header";
import "./App.scss";

class App extends Component {
  render() {
    const routes = [
      { component: Dashboard, path: "/dashboard" }
    ];

    return (
      <div className="App">
        <Router>
          <Header />
          <div className="container">
            <main>
              <Switch>
                {routes.map(route => (
                  <Route
                    key={route.path}
                    exect
                    path={route.path}
                    component={route.component}
                  />
                ))}
                <Redirect to="/dashboard" />
              </Switch>
            </main>
          </div>
        </Router>
      </div>
    );
  }
}

export default App;
