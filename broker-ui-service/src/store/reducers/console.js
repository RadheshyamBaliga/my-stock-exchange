import { updateObject } from "../../shared/utility";
import * as actionTypes from "../actions/actionTypes";

const initialState = {
  loading: false,
  responseData: null,
  error: false,
};

const reducer = (state = initialState, action) => {
  switch (action.type) {
    case actionTypes.NEW_ORDER_REQUEST_START:
      return updateObject(state, { loading: true, error: false });
    case actionTypes.NEW_ORDER_REQUEST_SUCCESS:
      return updateObject(state, {
        loading: false,
        responseData: action.responseData,
        error: false,
      });
    case actionTypes.NEW_ORDER_REQUEST_FAIL:
      return updateObject(state, {
        loading: false,
        error: action.error,
      });
    default:
      return state;
  }
};

export default reducer;
