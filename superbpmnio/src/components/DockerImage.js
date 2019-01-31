import React from "react";
import PropTypes from "prop-types";
import './DockerImage.css';

function DockerImage(props) {
    return (
        <div className="dockerImage">
            <span>{props.imageName}</span>
        </div>
    );
}

DockerImage.propTypes = {
    imageName: PropTypes.string.isRequired
}

export default DockerImage;