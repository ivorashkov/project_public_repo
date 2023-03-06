import { FormEvent, SyntheticEvent } from "react";
import { Section } from "../../components/Section";
import { register } from "../../services";
import { UserDetails } from "../../types";

export const Register = () => {
    // const onSubmitHandler  = (e: FormEvent<HTMLFormElement>) => {
    //     e.preventDefault();

    //     let { email, password }: FormData<UserDetails> = Object.fromEntries(new FormData(e.currentTarget));
    //     let email = formData.get('email');
    //     let password = formData.get('password');

    //     console.log(email)
    //     console.log(password)

    //     register<FormDataEntryValue | null>({email, password})
    //         .then(authData => {
    //             console.log(authData)
    //             // login(authData);
    //         })
    // };
    const onSubmitHandler = (e: FormEvent<HTMLFormElement>) => {
        e.preventDefault();

        const target = e.target as typeof e.target & {
          email: { value: string };
          password: { value: string };
        };

        const email = target.email.value; // typechecks!
        const password = target.password.value; // typechecks!

        register({email, password})
                .then(authData => {
                    console.log(authData)
                    // login({email, password});
                })
      }


    return (
        <Section>
            <div className="form">
                <h2>Register</h2>
                
                <div className="form__content">
                    <form action="" onSubmit={onSubmitHandler}>
                        <div className="form__row">
                            <label htmlFor="email">email</label>
                            <input type="email" name="email" id="" required/>
                        </div>

                        <div className="form__row">
                            <label htmlFor="password">password</label>
                            <input type="password" name="password" id="" required/>
                        </div>

                        <div className="form__actions">
                            <button>Submit</button>
                        </div>
                    </form>
                </div>
            </div>
        </Section>
    );
};
