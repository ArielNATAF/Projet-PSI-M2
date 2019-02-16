import React, { Component } from 'react';
import Dropzone from 'react-dropzone';

const maxSize = 10000 //bytes

class BPMNDropzone extends Component{

    handleOnDrop = (files, rejectedFiles) =>{
        console.log('files', files)
        console.log('rejectedfiles', rejectedFiles)
    }

    render(){
        return(
            <div>
                <h1> Drop file</h1>
                <Dropzone onDrop={this.handleOnDrop} accept='.bpmn' multiple={true} maxSize={maxSize}>
                    {({ getRootProps, getInputProps }) => (
                        <div {...getRootProps()}>
                            <input {...getInputProps()} />
                            <p>Déposer fichier ici.<br/>Fichiers .bpmn uniquement</p>
                        </div>
                    )}

                </Dropzone>
            </div>
        )
    }
}

export default BPMNDropzone