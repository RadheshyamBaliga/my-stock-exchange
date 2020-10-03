import React, { Component } from "react";
import { connect } from "react-redux";

import { AgGridReact } from "@ag-grid-community/react";
import { ClientSideRowModelModule } from "@ag-grid-community/client-side-row-model";
import "@ag-grid-community/all-modules/dist/styles/ag-grid.css";
import "@ag-grid-community/all-modules/dist/styles/ag-theme-alpine.css";

import * as actions from "../../store/actions/index";
import Spinner from "../../components/UI/Spinner/Spinner";

class Orders extends Component {
  constructor() {
    super();

    this.state = {
      columnDefs: this.createColumnDefs(),
      rowData: null,
      style: {
        width: "100%",
        height: "100%",
      },
      modules: [ClientSideRowModelModule],
      defaultColDef: {
        editable: true,
        sortable: true,
        flex: 1,
        minWidth: 100,
        filter: true,
        resizable: true,
        headerComponentParams: {
          menuIcon: "fa-bars",
        },
      },
    };
  }

  onGridReady(params) {
    this.gridApi = params.api;
    console.log("### " + this.props.orders);
    this.setState({ rowData: this.props.orders });
  }

  componentDidMount() {
    this.props.onFetchOrders();
  }

  createColumnDefs() {
    return [
      {
        headerName: "Broker Order ID",
        field: "brokerOrderId",
        filter: "agTextColumnFilter",
      },
      {
        headerName: "Order ID",
        field: "orderId",
        filter: "agTextColumnFilter",
      },
      { headerName: "ISIN Code", field: "isin", filter: "agTextColumnFilter" },
      {
        headerName: "Status",
        field: "orderStatus",
        sortable: true,
        sortingOrder: ["asc", "desc"],
        filter: "agTextColumnFilter",
      },
      {
        headerName: "Side",
        field: "side",
        sortable: true,
        sortingOrder: ["asc", "desc"],
      },
      {
        headerName: "Quantity",
        field: "quantity",
        sortable: true,
        sortingOrder: ["asc", "desc"],
        filter: "agNumberColumnFilter",
      },
      {
        headerName: "Open Quantity",
        field: "openQuantity",
        sortable: true,
        sortingOrder: ["asc", "desc"],
        filter: "agNumberColumnFilter",
      },
      {
        headerName: "Price",
        field: "price",
        sortable: true,
        sortingOrder: ["asc", "desc"],
        filter: "agNumberColumnFilter",
      },
      {
        headerName: "Entry Time",
        field: "entryTime",
        sortable: true,
        sort: "desc",
        sortingOrder: ["asc", "desc"],
      },
      { headerName: "Broker", field: "brokerId" },
    ];
  }

  render() {
    console.log(this.props.loading);
    if (!this.props.loading) {
      console.log(this.props.orders);
      return (
        <>
          <div style={{ height: "100%" }} className="ag-theme-alpine">
            <div style={this.state.style}>
              <AgGridReact
                columnDefs={this.state.columnDefs}
                rowData={this.state.rowData}
                modules={this.state.modules}
                onGridReady={this.onGridReady.bind(this)}
                animateRows={true}
                defaultColDef={this.state.defaultColDef}
              />
            </div>
          </div>
        </>
      );
    } else {
      return <Spinner />;
    }
  }
}

const mapStateToProps = (state) => {
  return {
    orders: state.orders.orders,
    loading: state.orders.loading,
  };
};

const mapDispatchToProps = (dispatch) => {
  return {
    onFetchOrders: () => dispatch(actions.fetchOrders()),
  };
};

export default connect(mapStateToProps, mapDispatchToProps)(Orders);
