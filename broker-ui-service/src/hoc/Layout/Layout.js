import React, { Component } from "react";

import Header from "../../components/UI/Header/Header";
import * as classes from "./Layout.module.css";

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
