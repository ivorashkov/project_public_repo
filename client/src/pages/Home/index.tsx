import { Grid } from "../../components/Grid";
import { Section } from "../../components/Section";
import { Shell } from "../../components/Shell";



export const Home = () => {
    const dummyObj = 
        [
                {
                    title: 'title 1',
                    description: 'ala bala 1'
                },
                {
                    title: 'title 2',
                    description: 'ala bala 2'
                },
                {
                    title: 'title 3',
                    description: 'ala bala 3'
                },
                {
                    title: 'title 4',
                    description: 'ala bala 4'
                },
                {
                    title: 'title 5',
                    description: 'ala bala 5'
                },
                {
                    title: 'title 6',
                    description: 'ala bala 6'
                }
        ];

    return (
        <>
            <Section>
                <Shell>
                    {/* <Grid offers={dummyObj}/> */}
                    <h1>test</h1>
                </Shell>
            </Section>
        </>
    );
}