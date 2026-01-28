import { Routes, Route } from "react-router-dom";
import Navbar from "./Components/Navbar";

import Login from "./pages/Login";
import Register from "./pages/Register";
import Plans from "./pages/Plans";
import Subscriptions from "./pages/Subscriptions";
import Payments from "./pages/Payments";
import Subscribe from "./pages/Subscribe";

function App() {
  return (
    <>
      <Navbar />

      <Routes>
        <Route path="/" element={<Register />} />
        <Route path="/login" element={<Login />} />
        <Route path="/plans" element={<Plans />} />
        <Route path="/subscriptions" element={<Subscriptions />} />
        <Route path="/payments" element={<Payments />} />
        <Route path="/subscribe" element={<Subscribe />} />
      </Routes>
    </>
  );
}

export default App;
