import React, { Component } from "react";
import set from "lodash-es/set";
import Rating from "react-rating";

class CommentForm extends Component {
  state = {
    text: "",
    isCommenting: false,
    score: 10
  };

  submitHandler() {
    const { submitHanlder } = this.props;
    submitHanlder(this.state);
    this.setState({ text: "", score: 10 });
  }

  handleChange(value, id) {
    const stateCopy = { ...this.state };
    set(stateCopy, id, value);
    this.setState(stateCopy);
  }

  render() {
    const { text, isCommenting, score } = this.state;
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
            <div className="form-group d-flex flex-column">
              <label htmlFor="text">Rate:</label>
              <i class="fas fa-ad" />
              <Rating
                initialRating={score}
                stop={10}
                onChange={value => this.setState({ score: value })}
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
