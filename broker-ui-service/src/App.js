import React, { Component } from "react";
import Layout from "./hoc/Layout/Layout";
import { Redirect, Route, Switch, withRouter } from "react-router";

import Orders from "./containers/Orders/Orders";
import Console from "./containers/Console/Console";
import OrderBook from "./containers/OrderBook/OrderBook";
import Trades from "./containers/Trades/Trades";

class App extends Component {
  render() {
    let routes = (
      <Switch>
        <Route path="/orders" component={Orders}></Route>
        <Route path="/orderbook" component={OrderBook}></Route>
        <Route path="/trades" component={Trades}></Route>
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
