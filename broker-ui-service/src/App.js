import React, { Component } from "react";
import Layout from "./hoc/Layout/Layout";
import { Redirect, Route, Switch, withRouter } from "react-router";

import "react-toastify/dist/ReactToastify.css";

import Orders from "./containers/Orders/Orders";
import Console from "./containers/Console/Console";
import OrderBook from "./containers/OrderBook/OrderBook";
import Executions from "./containers/Executions/Executions";

class App extends Component {
  render() {
    let routes = (
      <Switch>
        <Route path="/orders" component={Orders}></Route>
        <Route path="/orderbook" component={OrderBook}></Route>
        <Route path="/executions" component={Executions}></Route>
        <Route path="/" exact component={Console}></Route>
        <Redirect to="/" />
      </Switch>
    );
    return (
      <div>
        <Layout>{routes}</Layout>
      </div>
    );
  }
}

export default withRouter(App);
