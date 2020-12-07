import * as actionTypes from "../actions/actionTypes";
import { updateObject } from "../../shared/utility";

const initialState = {
  executions: [],
  loading: false,
};

const reducer = (state = initialState, action) => {
  switch (action.type) {
    case actionTypes.FETCH_EXECUTIONS_START:
      return updateObject(state, { loading: true });
    case actionTypes.FETCH_EXECUTIONS_SUCCESS:
      return updateObject(state, {
        executions: action.executions,
        loading: false,
      });
    case actionTypes.FETCH_EXECUTIONS_FAIL:
      return updateObject(state, { loading: false });
    default:
      return state;
  }
};

export default reducer;
