import React from "react";
import ResultVerif from "./ResultVerif";

function ResultVerifList(props) {
    return (
        <div>
            {props.resultVerifs.map(d => <ResultVerif
                parameter={d.parameter}
                comment={d.comment}
                time={d.time}
                key={d.time}
                imageId={d.imageId}/>)}
        </div>
    );
}

export default ResultVerifList;