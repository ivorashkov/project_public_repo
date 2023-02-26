import { Section } from "../../components/Section";
import { Shell } from "../../components/Shell";

export const Register = () => {
    return (
        <Section>
            <Shell>
                <div className="form">
                    <h2>Register</h2>
                    
                    <div className="form__content">
                        <form action="">
                            <label htmlFor="email"></label>
                            <input type="email" name="email" id="" />
                            <label htmlFor="password"></label>
                            <input type="password" name="password" id="" />
                        </form>
                    </div>
                </div>
            </Shell>
        </Section>
    );
};
