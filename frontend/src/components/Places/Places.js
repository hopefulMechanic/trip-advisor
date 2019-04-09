import React, { Component } from "react";
import { withRouter } from "react-router-dom";
import Card from "../../common/Card/Card";
import Collection from "../../common/Collection/Collection";
class Places extends Component {
  state = {};

  mapPlaceToRow(el) {
    return (
      <div key={el.id} className="d-flex">
        <div className="font-weight-bold">Name:</div>
        <div className="px-1">{el.name}</div>
      </div>
    );
  }

  render() {
    const { history, data = [] } = this.props;

    return (
      <div className="places">
        <Card header="Places List">
          <Collection
            onDoubleClick={id => {
              history.push(`/places/${id}`);
            }}
            list={data.map(el => ({
              id: el.id,
              content: this.mapPlaceToRow(el)
            }))}
          />
        </Card>
      </div>
    );
  }
}

export default withRouter(Places);
