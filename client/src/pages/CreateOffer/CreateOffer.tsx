import { Section } from "../../components/Section";
import { Shell } from "../../components/Shell";
import { OfferData } from "../../types";

export const CreateOffer = () => {

    const submitOnClick = (e: React.FormEvent<HTMLFormElement & EventTarget> ,data: OfferData) => {
        e.preventDefault();
        let formData = new FormData(e.currentTarget);
    }

    return (
        <Section>
            <Shell>
                <div className="form" onSubmit={submitOnClick}>
                    <h2>Create an offer</h2>

                    <div className="form__content">
                        <form action="">
                            <div className="form__row">
                                <label htmlFor="title">Title</label>

                                <input type="text" name="title" id="" />
                            </div>

                            <div className="form__row">
                                <label htmlFor="country">Country</label>

                                <input type="text" name="country" id="" />
                            </div>

                            <div className="form__row">
                                <label htmlFor="city">City</label>

                                <input type="text" name="city" id="" />
                            </div>

                            <div className="form__row">
                                <label htmlFor="duration">duration</label>

                                <input type="number" name="duration" id="" />
                            </div>

                            <div className="form__row">
                                <label htmlFor="stars">hotel stars</label>

                                <input type="number" name="stars" id="" />
                            </div>

                            <div className="form__row">
                                <label htmlFor="price">price</label>

                                <input type="number" name="price" id="" />
                            </div>

                            <div className="form__row">
                                <label htmlFor="description">description</label>

                                <input type="text" name="description" id="" />
                            </div>

                            <div className="form__row">
                                <label htmlFor="discount">discount</label>

                                <input type="number" name="discount" id="" />
                            </div>

                            <div className="form__row">
                                <label htmlFor="transport">transport</label>

                                <select name="transport" id="">
                                    <option value="airplane">Airplane</option>
                                    <option value="buss">Buss</option>
                                    <option value="ship">Ship</option>
                                </select>
                            </div>

                            <div className="form__actions">
                                <button>Submit</button>
                            </div>
                        </form>
                    </div>
                </div>
            </Shell>
        </Section>
    );
}