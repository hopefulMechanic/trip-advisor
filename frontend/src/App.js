import React, { Component } from "react";
import {
  BrowserRouter as Router,
  Route,
  Switch,
  Redirect
} from "react-router-dom";
import { connect } from "react-redux";

import Places from "./components/Places/Places";
import Header from "./components/Header/Header";
import "./App.scss";
import PlaceDetail from "./components/Places/PlaceDetail/PlaceDetail";
import PlaceForm from "./components/Places/PlaceForm/PlaceForm";
class App extends Component {
  render() {
    const { isAuthenticated } = this.props;
    return (
      <div className="App">
        <Router>
          <Header />
          <main className="container">
            <Switch>
              <Route path={"/places"} exact component={Places} />
              <Route path={"/places/new"} exact component={PlaceForm} />
              <Route path={"/places/:id/edit"} exact component={PlaceForm} />
              <Route path={"/places/:id"} exact component={PlaceDetail} />
              <Redirect to="/places" />
            </Switch>
          </main>
        </Router>
      </div>
    );
  }
}
const mapStateToProps = state => ({
  isAuthenticated: state.auth.isAuthenticated
});
export default connect(mapStateToProps)(App);
