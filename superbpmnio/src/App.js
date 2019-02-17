import React, { Component } from 'react';
//import logo from './logo.svg';
import './App.css';
import DockerImageList from "./components/DockerImageList";
import FileUploader from "./components/fileUploader"
import axios from "axios";
//import BPMNDropzone from './BPMNDropzone';
import request from "superagent";
import ResultVerifList from './components/ResultVerifList';

const footerStyle = {
  backgroundColor: "black",
  fontColor: "white",
  fontSize: "12px",
  color: "white",
  borderTop: "1px solid #E7E7E7",
  textAlign: "center",
  padding: "13px",
  position: "fixed",
  left: "0",
  bottom: "0",
  height: "10px",
  width: "100%"
};

const phantomStyle = {
  display: "block",
  padding: "13px",
  height: "10px",
  width: "100%"
};

function Footer({ children }) {
  return (
    <div>
      <div style={phantomStyle} />
      <div style={footerStyle}>{children}</div>
    </div>
  );
}

class App extends Component {

  onDrop = (files) => {
    // POST to a test endpoint for demo purposes
    const req = request.post('https://localhost:8080/files');
    files.forEach(file => {
      req.attach(file.name, file);
    });
    req.end();
  }

  state = {
    dockerImages: [],
    resultVerifs : []
  };

  componentDidMount() {
/*    axios
      .get("http://localhost:8080/dockerImages")
      .then(response => {
        console.log(response);
        // create an array of contacts only with relevant data
        const newDockerImages = response.data.map(c => {
          return {
            id: c.id,
            imageName: c.imageName,
            dockerfilePath: c.dockerfilePath,
            containerFile: c.containerFile,
            imageId: c.imageId
          };
        });
        // create a new "State" object without mutating 
        // the original State object. 
        const newState = Object.assign({}, this.state, {
          dockerImages: newDockerImages
        });
        // store the new state object in the component's state
        this.setState(newState);
      })
      .catch(error => console.log(error));
*/
    axios
      .get("http://localhost:8080/resultVerifs")
      .then(response => {
        console.log(response);
        const newResultVerifs = response.data.map(c => {
          return {
            Id_result: c.Id_result,
            imageId: c.imageId,
            time: c.time,
            parameter: c.parameter,
            comment: c.comment
          };
        });
        const newState = Object.assign({}, this.state, {
          resultVerifs: newResultVerifs
        });
        this.setState(newState);
      })
      .catch(error => console.log(error));
  }

  render() {
    return (
      <div className="App">
        <img src={require('./logo.png')} alt="logo" />
        <DockerImageList dockerImages = {this.state.dockerImages}/>
        <FileUploader/>
        <ResultVerifList resultVerifs={this.state.resultVerifs} />
        <Footer><span>Ariel Nataf - 2019 - Projet PSI - GPLv3 - https://github.com/ArielNATAF/Projet-PSI-M2</span></Footer>
      </div>
    );
  }
}

export default App;
