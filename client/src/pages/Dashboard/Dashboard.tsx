import { Link, Outlet } from "react-router-dom";
import { FormEvent, SetStateAction, useContext, useState } from "react";
import { AuthContext } from "../../context";

export const Dashboard = () => {
  const [files, setFiles] = useState<any>();
  const data = useContext(AuthContext);

  const onSubmitHandler = async (e: FormEvent<HTMLFormElement>) => {
    e.preventDefault();

    const formData = new FormData(e.currentTarget);
    formData.append("file", files);

    await fetch(
      `http://localhost:8091/api/user/upload/all/${data?.userData?.id}`,
      {
        mode: "no-cors",
        method: "POST",
        headers: {
          Authorization: `Bearer ${data?.token}`,
        },
        body: formData,
      }
    )
      .then((res) => res)
      .then((result) => console.log(result))
      .catch((err) => console.error(err));
  };

  console.log(files);

  const activeSections = (
    <>
      <p>gosho, tosho</p>
    </>
  );

  const inactiveSections = (
    <>
      <div className="note">
        <p>plis upload files u asshole, {data?.userData?.sub}!</p>
      </div>

      <div className="form">
        <form
          action=""
          onSubmit={onSubmitHandler}
          encType="multipart/form-data"
        >
          <div className="form__row">
            <label htmlFor="file">Select Files *</label>

            <input
              type="file"
              name="file"
              id="file"
              onChange={(event) => setFiles(event.target.files)}
              multiple
              required
            />
          </div>

          <button className="btn">submit</button>
        </form>
      </div>
    </>
  );

  return (
    <section>
      {data?.userData?.status == "inactive" ? inactiveSections : activeSections}
    </section>
  );
};
