import { Link, Outlet } from 'react-router-dom';
import {FormEvent, useContext} from "react";
import {AuthContext} from "../../context";

export const Dashboard = () => {
  const data = useContext(AuthContext);

  const onSubmitHandler = (e:FormEvent<HTMLFormElement>) => {
    e.preventDefault();

    const formData = new FormData(e.currentTarget);

    const formFields = {
      file: formData.get('file')
    }


  }

  const activeSections = <>
    <p>gosho, tosho</p>
  </>;

  const inactiveSections = <>
    <div className="form">
      <form action="" onSubmit={onSubmitHandler}>
        <div className="form__row">
          <label htmlFor="file">Select Files</label>

          <input type="file" name="file" id="file"  multiple/>
        </div>

        <button>
          submit
        </button>
      </form>
    </div>
  </>;

  return (
    <section>
      {data?.userData?.status == 'inactive' ? inactiveSections : activeSections }
    </section>
  );
};
