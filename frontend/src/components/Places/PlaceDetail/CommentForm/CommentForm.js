import React, { Component } from "react";
import set from "lodash-es/set";

class CommentForm extends Component {
  state = {
    text: ""
  };

  submitHandler() {
    // const { addPlace, history } = this.props;
    // addPlace(this.state, history);
  }

  handleChange(value, id) {
    const stateCopy = { ...this.state };
    set(stateCopy, id, value);
    this.setState(stateCopy);
  }

  render() {
    const { text } = this.props;
    return (
      <form
        className="my-2"
        onSubmit={event => {
          this.submitHandler();
          event.preventDefault();
        }}
      >
        <div className="form-group">
          <label htmlFor="text">Text:</label>
          <textarea
            onChange={event => this.handleChange(event.target.value, "text")}
            value={text}
            className="form-control"
            id="text"
          />
        </div>
        <button
          type="button"
          className="btn btn-primary"
          disabled={text === ""}
          onClick={() => this.submitHandler()}
        >
          Submit
        </button>
      </form>
    );
  }
}

export default CommentForm;
