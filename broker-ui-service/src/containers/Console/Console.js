import React, { Component } from "react";
import { connect } from "react-redux";

import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

import * as classes from "./Console.module.css";
import * as actions from "../../store/actions/index";
import Spinner from "../../components/UI/Spinner/Spinner";

class Console extends Component {
  initialNewOrderForm = {
    isincode: "",
    side: "BUY",
    brokerOrderId: "",
    quantity: 0,
    price: 0,
  };
  state = {
    newOrderForm: this.initialNewOrderForm,
  };

  onFormSubmit = (event) => {
    event.preventDefault();
    console.log("On SUBMIT");
    if (this.validateFormFields()) {
      this.props.onNewOrderSend(this.state.newOrderForm);
    }
  };

  notify = (msg) => toast.error(msg);

  validateFormFields() {
    if (this.state.newOrderForm.isincode.length !== 12) {
      this.notify("Please enter valid ISIN Code!");
      return false;
    }
    if (this.state.newOrderForm.quantity <= 0) {
      this.notify("Please enter positive non-zero Quantity!");
      return false;
    }
    if (this.state.newOrderForm.price <= 0) {
      this.notify("Please enter positive non-zero Price!");
      return false;
    }
    return true;
  }

  isinCodeChangeHandler = (event) => {
    const ordForm = { ...this.state.newOrderForm };
    ordForm.isincode = event.target.value.toUpperCase();
    this.setState({ ...this.state, newOrderForm: ordForm });
  };

  sideChangeHandler = (event) => {
    const ordForm = { ...this.state.newOrderForm };
    ordForm.side = event.target.value;
    this.setState({ ...this.state, newOrderForm: ordForm });
  };

  brokerOrderIdChangeHandler = (event) => {
    const ordForm = { ...this.state.newOrderForm };
    ordForm.brokerOrderId = event.target.value.toUpperCase();
    this.setState({ ...this.state, newOrderForm: ordForm });
  };

  quantityChangeHandler = (event) => {
    const ordForm = { ...this.state.newOrderForm };
    ordForm.quantity = event.target.value.toUpperCase();
    this.setState({ ...this.state, newOrderForm: ordForm });
  };

  priceChangeHandler = (event) => {
    const ordForm = { ...this.state.newOrderForm };
    ordForm.price = event.target.value.toUpperCase();
    this.setState({ ...this.state, newOrderForm: ordForm });
  };

  resetNewOrderForm = () => {
    this.setState({ ...this.state, newOrderForm: this.initialNewOrderForm });
  };

  render() {
    return (
      <>
        <ToastContainer pauseOnHover />
        <div className={classes.ConsoleHeader}>
          <h3>NEW ORDER</h3>
        </div>
        <div className={classes.Console}>
          {this.props.error ? (
            <div
              className="alert alert-danger alert-dismissible fade show"
              role="alert"
            >
              <strong>Oh snap!</strong> New Order Request Failed!
            </div>
          ) : null}

          {this.props.responseData ? (
            <div
              className="alert alert-success alert-dismissible fade show"
              role="alert"
            >
              <strong>Success!</strong> Order placed Successfully!!
            </div>
          ) : null}

          <form onSubmit={this.onFormSubmit}>
            <div className="form-group row">
              <label htmlFor="isincode" className="col-sm-2 col-form-label">
                ISIN Code
              </label>
              <div className="col-sm-10">
                <input
                  type="text"
                  className="form-control"
                  id="isincode"
                  required={true}
                  value={this.state.newOrderForm.isincode}
                  onChange={(e) => this.isinCodeChangeHandler(e)}
                  maxLength="12"
                />
              </div>
            </div>
            <div className="form-group row">
              <label htmlFor="side" className="col-sm-2 col-form-label">
                Side
              </label>
              <div className="col-sm-10">
                <select
                  className="form-control"
                  id="side"
                  required={true}
                  value={this.state.newOrderForm.side}
                  onChange={(e) => this.sideChangeHandler(e)}
                >
                  <option>BUY</option>
                  <option>SELL</option>
                </select>
              </div>
            </div>
            <div className="form-group row">
              <label
                htmlFor="brokerorderid"
                className="col-sm-2 col-form-label"
              >
                Broker Order ID
              </label>
              <div className="col-sm-10">
                <input
                  type="text"
                  className="form-control"
                  id="brokerorderid"
                  required={true}
                  value={this.state.newOrderForm.brokerOrderId}
                  onChange={(e) => this.brokerOrderIdChangeHandler(e)}
                />
              </div>
            </div>
            <div className="form-group row">
              <label htmlFor="quantity" className="col-sm-2 col-form-label">
                Quantity
              </label>
              <div className="col-sm-10">
                <input
                  type="number"
                  className="form-control"
                  id="quantity"
                  required={true}
                  value={this.state.newOrderForm.quantity}
                  onChange={(e) => this.quantityChangeHandler(e)}
                />
              </div>
            </div>
            <div className="form-group row">
              <label htmlFor="price" className="col-sm-2 col-form-label">
                Price
              </label>
              <div className="col-sm-10">
                <input
                  type="number"
                  className="form-control"
                  id="price"
                  required={true}
                  value={this.state.newOrderForm.price}
                  onChange={(e) => this.priceChangeHandler(e)}
                />
              </div>
            </div>
            <div className="form-group row">
              <div className="col-sm-2"></div>
              <div className="col-sm-10">
                {!this.props.loading ? (
                  <button className="btn btn-primary mr-3" type="submit">
                    SEND
                  </button>
                ) : (
                  <button
                    className="btn btn-primary mr-3"
                    type="button"
                    disabled
                  >
                    <span
                      className="spinner-grow spinner-grow-sm"
                      role="status"
                      aria-hidden="true"
                    ></span>
                    &nbsp;Sending...
                  </button>
                )}

                <button
                  className="btn btn-secondary"
                  type="button"
                  onClick={this.resetNewOrderForm}
                  disabled={this.props.loading}
                >
                  RESET
                </button>
              </div>
            </div>
          </form>
        </div>
      </>
    );
  }
}

const mapStateToProps = (state) => {
  return {
    loading: state.console.loading,
    error: state.console.error,
    responseData: state.console.responseData,
  };
};

const mapDispatchToProps = (dispatch) => {
  return {
    onNewOrderSend: (newOrderForm) =>
      dispatch(actions.newOrderRequest(newOrderForm)),
  };
};

export default connect(mapStateToProps, mapDispatchToProps)(Console);
