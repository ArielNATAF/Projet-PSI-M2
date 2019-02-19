import React from "react";
import PropTypes from "prop-types";
import './ResultVerif.css';

function ResultVerif(props) {
    return (
        <div className="resultVerif">
            <span>{props.time}</span>
            <p/>
            <span>{props.comment}</span>
        </div>
    );
}

ResultVerif.propTypes = {
    comment: PropTypes.string.isRequired
}
export default ResultVerif;