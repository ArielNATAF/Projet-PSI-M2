import React from "react";
import DockerImage from "./DockerImage";

function DockerImageList(props) {
    return (
        <div>
            {props.dockerImages.map(d => <DockerImage
                key={d.id}
                imageName={d.imageName}
                dockerfilePath={d.dockerfilePath}
                containerFile={d.containerFile}
                imageId={d.imageId}/>)}
        </div>
    );
}

export default DockerImageList;