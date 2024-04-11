import './App.css';
import Sidebar from "./components/sidebar/Sidebar";
import Content from "./components/Content";
function App() {
  return (
    <div className="App flex">
        <Sidebar/>
        <Content/>
    </div>
  );
}
export default App;