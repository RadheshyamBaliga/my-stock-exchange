import React, { Component } from "react";
import classes from "./Layout.module.css";

import Header from "../../components/UI/Header/Header";

class Layout extends Component {
  render() {
    return (
      <>
        <Header></Header>
        <main>{this.props.children}</main>
      </>
    );
  }
}

export default Layout;
