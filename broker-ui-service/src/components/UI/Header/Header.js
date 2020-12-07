import React from "react";
import { NavLink } from "react-router-dom";

const header = () => {
  return (
    <div>
      <nav className="navbar navbar-expand-lg navbar-dark bg-dark justify-content-between sticky-top">
        <a className="navbar-brand" style={{ color: "salmon" }} href="/">
          MY EXCHANGE
        </a>
        <button
          className="navbar-toggler"
          type="button"
          data-toggle="collapse"
          data-target="#navbarSupportedContent"
          aria-controls="navbarSupportedContent"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <span className="navbar-toggler-icon"></span>
        </button>
        <div className="collapse navbar-collapse" id="navbarSupportedContent">
          <ul className="navbar-nav mr-auto">
            <li className="nav-item px-4">
              <NavLink className="nav-link" exact to="/">
                Console
              </NavLink>
            </li>
            <li className="nav-item px-4">
              <NavLink className="nav-link" to="/orders">
                Orders
              </NavLink>
            </li>
            <li className="nav-item px-4">
              <NavLink className="nav-link" to="/executions">
                Executions
              </NavLink>
            </li>
            <li className="nav-item px-4">
              <NavLink className="nav-link" to="/orderbook">
                OrderBook
              </NavLink>
            </li>
          </ul>
        </div>
        <div>
          <ul className="navbar-nav mr-auto">
            <li className="nav-item dropdown">
              <a
                className="nav-link dropdown-toggle"
                href="#"
                id="navbarDropdown"
                role="button"
                data-toggle="dropdown"
                aria-haspopup="true"
                aria-expanded="false"
              >
                Broker ID
              </a>
              <div className="dropdown-menu" aria-labelledby="navbarDropdown">
                <a className="dropdown-item" href="#">
                  Profile
                </a>
                <a className="dropdown-item" href="#">
                  Help
                </a>
                <div className="dropdown-divider"></div>
                <a className="dropdown-item" href="#">
                  Logout
                </a>
              </div>
            </li>
          </ul>
        </div>
      </nav>
    </div>
  );
};
export default header;
