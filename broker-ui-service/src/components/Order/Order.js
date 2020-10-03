import React from "react";

const order = (props) => {
  return (
    <div>
      <span>OrderID: {props.orderId}</span>
      <span>ISIN: {props.isin}</span>
    </div>
  );
};

export default order;
