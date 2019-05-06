import React, { Component } from "react";
import set from "lodash-es/set";

class CommentForm extends Component {
  state = {
    text: "",
    isCommenting: false
  };

  submitHandler() {
    const { submitHanlder } = this.props;
    submitHanlder(this.state);
    this.setState({ text: "" });
  }

  handleChange(value, id) {
    const stateCopy = { ...this.state };
    set(stateCopy, id, value);
    this.setState(stateCopy);
  }

  render() {
    const { text, isCommenting } = this.state;
    return (
      <>
        <button
          className="btn btn-secondary"
          onClick={() => {
            this.setState({ isCommenting: !isCommenting });
          }}
        >
          +
        </button>
        {isCommenting && (
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
                onChange={event =>
                  this.handleChange(event.target.value, "text")
                }
                value={text}
                className="form-control"
                id="text"
              />
            </div>
            <button
              type="button"
              className="btn btn-secondary"
              disabled={text === ""}
              onClick={() => this.submitHandler()}
            >
              Save
            </button>
          </form>
        )}
      </>
    );
  }
}

export default CommentForm;
