import React, { Component } from "react";
import { connect } from "react-redux";

import { AgGridReact } from "@ag-grid-community/react/lib/agGridReact";
import { ClientSideRowModelModule } from "@ag-grid-community/client-side-row-model";

import Spinner from "../../components/UI/Spinner/Spinner";
import * as actions from "../../store/actions/index";

class Executions extends Component {
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
    this.setState({ rowData: this.props.executions });
  }

  componentDidMount() {
    this.props.onFetchExecutions();
  }

  createColumnDefs() {
    return [
      {
        headerName: "Execution ID",
        field: "executionId",
        filter: "agTextColumnFilter",
      },
      {
        headerName: "Quantity",
        field: "quantity",
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
        headerName: "Execution Time",
        field: "createdtime",
        sortable: true,
        sort: "desc",
        sortingOrder: ["asc", "desc"],
      },
      {
        headerName: "Cross Order - Buy",
        field: "buyOrderId",
        filter: "agTextColumnFilter",
      },
      {
        headerName: "Cross Order - Sell",
        field: "sellOrderId",
        filter: "agTextColumnFilter",
      },
    ];
  }

  render() {
    console.log(this.props.loading);
    if (!this.props.loading) {
      console.log(this.props.executions);
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
    executions: state.executions.executions,
    loading: state.executions.loading,
  };
};

const mapDispatchToProps = (dispatch) => {
  return {
    onFetchExecutions: () => dispatch(actions.fetchExecutions()),
  };
};

export default connect(mapStateToProps, mapDispatchToProps)(Executions);
