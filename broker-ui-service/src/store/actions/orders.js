import axios from "../../axios";
import data from "../../data/orders.json";

import * as actionTypes from "./actionTypes";

export const fetchOrdersStart = () => {
  return {
    type: actionTypes.FETCH_ORDERS_START,
  };
};

export const fetchOrdersSuccess = (orders) => {
  return {
    type: actionTypes.FETCH_ORDERS_SUCCESS,
    orders: orders,
  };
};

export const fetchOrdersFail = (error) => {
  return {
    type: actionTypes.FETCH_ORDERS_FAIL,
    error: error,
  };
};

export const fetchOrders = () => {
  return (dispatch) => {
    dispatch(fetchOrdersStart());
    axios
      .get("/exchange-service/orders")
      .then((response) => {
        console.log("Success");
        dispatch(fetchOrdersSuccess(response.data));
      })
      .catch((error) => {
        console.log("Error " + error);
        dispatch(fetchOrdersFail(error));
      });

    // setTimeout(() => {
    //   dispatch(fetchOrdersSuccess(data));
    // }, 2000);
  };
};
