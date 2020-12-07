import axios from "../../axios";

import * as actionTypes from "./actionTypes";

export const newOrderRequest = (newOrderFormData) => {
  return (dispatch) => {
    dispatch(newOrderRequestStart());
    axios
      .post("/broker-service/neworder", newOrderFormData)
      .then((response) => {
        console.log("resp ", response.data);
        dispatch(newOrderRequestSuccess(response.data));
      })
      .catch((error) => {
        dispatch(newOrderRequestFail(error));
      });
  };
};

const newOrderRequestStart = () => {
  return {
    type: actionTypes.NEW_ORDER_REQUEST_START,
  };
};

const newOrderRequestSuccess = (responseData) => {
  return {
    type: actionTypes.NEW_ORDER_REQUEST_SUCCESS,
    responseData: responseData,
  };
};

const newOrderRequestFail = (error) => {
  return {
    type: actionTypes.NEW_ORDER_REQUEST_FAIL,
    error: error,
  };
};
