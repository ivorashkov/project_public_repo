import { Routes, Route } from 'react-router-dom';
import { Wrapper } from './components/Wrapper';
import { Header } from './components/Header';
import { Home } from './pages/Home';
import { Register } from './pages/Register';
import { CreateOffer } from './pages/CreateOffer';

function App() {
  return (
   <Wrapper>
    <>
      <Header/>
    
      <Routes>
        <Route path='/' element={<Home/>} />
        <Route path='/register' element={<Register/>} />
        <Route path='/offer/create' element={<CreateOffer/>} />
      </Routes>

      {/* <Footer/> */}
    </>
   </Wrapper>
  );
}

export default App;
