import axios from "../../axios";

import * as actionTypes from "./actionTypes";
import data from "../../data/executions.json";
import { fetchOrdersFail } from "./orders";

export const fetchExecutionsStart = () => {
  return {
    type: actionTypes.FETCH_EXECUTIONS_START,
  };
};

export const fetchExecutionsSuccess = (executions) => {
  return {
    type: actionTypes.FETCH_EXECUTIONS_SUCCESS,
    executions: executions,
  };
};

export const fetchExecutionsFail = (error) => {
  return {
    type: actionTypes.FETCH_EXECUTIONS_FAIL,
    error: error,
  };
};

export const fetchExecutions = () => {
  return (dispatch) => {
    dispatch(fetchExecutionsStart());
    axios
      .get("/exchange-service/executions", { timeout: 30000 })
      .then((response) => {
        console.log("Success " + response.data);
        dispatch(fetchExecutionsSuccess(response.data));
      })
      .catch((error) => {
        console.log("Error " + error);
        dispatch(fetchOrdersFail(error));
      });

    // setTimeout(() => {
    //   dispatch(fetchExecutionsSuccess(data));
    // }, 2000);
  };
};
