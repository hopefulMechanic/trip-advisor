import React from "react";

const Modal = ({ id, title, closeCallBack, content, footer }) => {
  return (
    <div
      class="modal fade"
      id={id}
      tabindex="-1"
      role="dialog"
      aria-labelledby={title && title.toLowerCase().trim()}
      aria-hidden="true"
    >
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            {title && (
              <h5 class="modal-title" id={title.toLowerCase().trim()}>
                {title}
              </h5>
            )}
            <button
              type="button"
              class="close"
              data-dismiss="modal"
              aria-label="Close"
              onClick={() => closeCallBack && closeCallBack()}
            >
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <div class="modal-body">{content}</div>
          {footer && <div class="modal-footer">{footer}</div>}
        </div>
      </div>
    </div>
  );
};

export default Modal;
