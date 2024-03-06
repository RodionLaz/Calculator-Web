import React, { useState } from 'react';
import '../styles/calcoolator.css'
function Calculator() {

    const [numbers, setNumbers] = useState('');

    const addtoString = (num: string) => {
        setNumbers(numbers + num.toString());
        console.log(numbers)
    }
    const ClearNumbers = () =>{
        setNumbers('');
    }

    const Calculate = async (numbers:string) => {
        const response = await fetch("http://localhost:9000",{
            method: 'POST',
            headers: {
                'Content-Type': 'text/plain'
            },
            body: numbers,
        });
        const responseData = await response.text(); 
        setNumbers(responseData);
        return response
    }
    
  return (
    <div className="Calculator-container">
        <h1>calcoolator</h1>
        <b>{numbers}</b>
        
        <br></br>
        <table className='Calculator-buttons'>
            <tr>
                <td className='AC'>
                    <button onClick={() => addtoString('(')}>(</button>
                    <button onClick={() => ClearNumbers()}>AC</button>
                    <button onClick={() => addtoString(')')}>)</button>
                </td>
            </tr>
            <tr>
                <td>
                    <button onClick={() => addtoString('7')}>7</button>
                    <button onClick={() => addtoString('8')}>8</button>
                    <button onClick={() => addtoString('9')}>9</button>
                    <button onClick={() => addtoString('÷')}>÷</button>
                </td>
            </tr>
            <tr>
                <td>
                    <button onClick={() => addtoString('4')}>4</button>
                    <button onClick={() => addtoString('5')}>5</button>
                    <button onClick={() => addtoString('6')}>6</button>
                    <button onClick={() => addtoString('×')}>×</button>
                </td>
            </tr>
            <tr>
                <td>
                    <button onClick={() => addtoString('1')}>1</button>
                    <button onClick={() => addtoString('2')}>2</button>
                    <button onClick={() => addtoString('3')}>3</button>
                    <button onClick={() => addtoString('-')}>-</button>
                </td>
            </tr>
            <tr>
                <td>
                    <button onClick={() => Calculate(numbers)}>=</button>
                    <button onClick={() => addtoString('0')}>0</button>
                    <button onClick={() => addtoString('.')}>.</button>
                    <button onClick={() => addtoString('+')}>+</button>
                </td>
            </tr>
        </table>
    </div>
  );
}

export default Calculator;
