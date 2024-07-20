# Wegmans

This project is built with [next.js 14](https://nextjs.org/docs).

## Set up

### Installation

Install pnpm globally.

bash
npm i -g pnpm


bash
pnpm install


It is important that pnpm is used when adding new npm packages to the repository. It is OK to use standard npm for other processes however.

### Your Code Editor

Please use VS Code. This repo has required [VS Code extensions](.vscode/extensions.json) defined that should be automatically recommended when you first open the project.

The repo also has [VS Code settings](.vscode/settings.json) defined that should enable automatic formatting on save.

If automatic formatting on Save doesn’t work, please reach out so that we can debug it. Having consistent formatting substantially reduces the chances of merge conflicts.

### Running the project

bash
npm run dev


Open [http://localhost:3000](http://localhost:3000) with your browser to see the result.

> *Note*: If you experience issues with Hot Module Replacement, test the experience in an incognito window with no extensions active. Some Chrome extensions can break Next 13’s HMR.

### Environment Setup Help and Safeguards

#### Engines and .npmrc

Package.json has both NPM versions and Node versions specified in the engines property. There is a corresponding file in the project root named .npmrc. That file has one property set -> engine-strict=true. This property forces a developers environment to be correct before completing the (p)npm install command.

#### .nvmrc(Mac Only)

[Node Version Manager(NVM)](https://github.com/nvm-sh/nvm) allows you to install multiple versions of Node in parallel in your local environment. If you are using NVM you can create a file named .nvmrc that specifies the Node version number(XX.XX.X) that you want to use. Simply add the Node version and subversion numbers to be able to use it. When that is complete, you can run the command nvm use prior to running the code and it will either prompt you to install the required versions with nvm install XX.XX.X or it will automatically change to use the version specified.

This is up to the developer to use or not use and has been adding to the .gitignore file.

## Contributing

### Components

There are two types of components: *components* that live in the /components directory, and *pages*, which live in the /app directory. This section is all about *components*.

> **Run npm run plop to create new components.**

Plop will ask you what you’d like to name the component (it doesn't care if you use a space or dashes or proper-case it), then it outputs the following for you in a new folder in the components directory:

- a React component
- a Storybook story
- a Jest test
- an SCSS file

The Storybook file contains an export of Default.

> **Please update your component’s Default.args with dummy data so that your component can be mounted in Storybook and Jest. PR builds will break if this is not done.**

The Jest test comes with a check to make sure the component can be rendered without any errors, and that it passes aXe’s accessibility linting. The test uses the Default.args from the component’s stories file, so you probably won’t ever need to touch the test to cover this baseline.

#### Component Naming Guidelines

We are using a non-nested component folder structure that relies on naming conventions to keep components organized.

This pattern is established as a best practice within the Vue ecosystem, and we intend to follow many of the conventions defined there:

- [Tightly coupled component names](https://v2.vuejs.org/v2/style-guide/?redirect=true#Tightly-coupled-component-names-strongly-recommended)
- [Order of words in component names](https://v2.vuejs.org/v2/style-guide/?redirect=true#Order-of-words-in-component-names-strongly-recommended)
- [Full-word component names](https://v2.vuejs.org/v2/style-guide/?redirect=true#Full-word-component-names-strongly-recommended)

This structure can be challenging in that naming your components correctly is essential. We will attempt to provide expected component names in ADO tickets to mitigate this.

### Styling

Please see [docs/style-guides/styling.md](./docs/style-guides/styling.md). This is important but lengthy, so it's been extracted into it's own file.

### State Management

This project uses [Zustand](https://github.com/pmndrs/zustand) for state management.

Zustand is a substantially simpler state management system to work with than Redux. The persist wrapper enables us to keep a store up to date with localStorage, and the immer wrapper enables writing succinct store updates by mutating the store’s state directly rather than traditional Redux-like patterns of returning the entire state tree with modifications in place.

Zustand stores are contained in /stores/. Zustand can be debugged within React devtools. Each store will appear at the top of the tree. Stores are only debuggable if they are registered at the bottom of their store file.

### Hooks

React hooks are a big part of this project. We store all of our hooks in the /hooks folder.

There are two particularly important, very highly used hooks in this project:

- [useSWR](https://swr.vercel.app/), a third-party package that handle deduplication of API requests
- [useAuthenticationCheck](./hooks/useAuthenticationCheck.tsx), a hook that we can use to validate whether a user is logged in that will also fire a dialog if await authenticationCheck() returns false. (This functionality can be turned off as well)

The majority of our other hooks handle APIs via useSWR.

### APIs

All external APIs should be routed through APIM.

Our constants for APIM APIs can be found in [/config/api/apim-routes.ts](/config/api/apim-routes.ts). These are to be imported and used consistently so that we can manage version compatibility with the services.

For example:


`${API_CART}/carts/changestore?${API_CART_VERSION}`


### Storybook

Storybook is used to develop components outside of the main application. Every component within the components directory should have a story.ts file, with very few exceptions.

It is important that components are coded to maximize reuse. By developing within Storybook, we ensure that components are driven by their props rather than being bound directly to the application state. This makes testing various scenarios easier and enables us to develop a great deal of the site without backend integration. Code for Storybook first, then integrate into the app.

Run npm run storybook to boot it up.

### Material UI

Many components in the project come from [Material UI](https://mui.com/material-ui/).

We should use MUI where it is legitimately helpful, but many of our solutions will continue to be custom. It is not expected that we use MUI for everything.

*Avoid* using MUI for things like:

- Typography. Instead, use global classes that we've defined in styles/global/typography.scss
- UI spacing. Tailwind should be sufficient for our spacing needs, as everything in our designs use a 4px spacing system

### Icons

We do not have a standardized icon system in place, and the project contains several methods of placing icons on a page. This is not ideal, but our icons come in a variety of viewBox sizes and some are not mono-colored, so further analysis is required before attempting a refactor to a single system.

- Many of our icons require us to create a component. We're storing these in components/Icons/Icon[name].tsx. This is the most likely precedent we will keep as it is extremely flexible
- We have a components/SvgIcons set of icon components. Do not add to this
- [Material Icons](https://mui.com/material-ui/material-icons/) contains many of the icons that were used for Lift + Shift work, but they may not always match our designs. Using them is straightforward, where we can
- We have SVGs from Figma into our public/icons folder for reference. Some of these are currently injected via Next Image

### CMS - Contentstack

Contentstack is the CMS of choice. A utility file provides [Stack](utilities/content-stack/init.ts), an easy way for us to make serverside Contentstack queries via the Contentstack npm package.

Clientside Contentstack queries are only possible via a GraphQL APIM relay.

### Automated Testing

#### SonarQube’s code quality reports

When a PR is issued, SonarQube will analyze new code. We *do* want to care for SonarQube’s code quality checks, where SonarQube points out potential codesmells. However, as of March 2024, SonarQube is new to the project and its configuration may not be perfect. Take codesmells with a grain of salt and report them if they seem unhelpful.

#### Unit Tests

SonarQube’s code coverage reports can be ignored.

We are not currently attempting to hit a certain percentage threshold of lines covered by unit tests. These can often lead developers to writing idiosyncratic or unhelpful tests, and the need to change tests alongside components can slow development down.

In an attempt to only write legitimately helpful unit tests, here are some questions to ask before writing a unit test:

- Does this help *verify* something a *user wants to do*?
- Is this something I want to make sure *no one breaks*?
- Are there complicated functional requirements we need to *secure*?
- Would a unit test *help me* make sure *I wrote my code correctly*?

However, we do want all of our components to be capable of running and passing the baseline aXe accessibility test, so we may need to take care to handle mocks as needed in our unit tests, and we need to verify that we're running aXe accessibility tests on various states—for example, if a drawer is open or closed, or if a component has lots of populated props vs the minimum, or if a component has several modes.

#### End to End Tests

The QA team is responsible for adding end-to-end tests to the project. These will be the primary source of confidence in our application.

*However, E2E testing cannot function in our project’s current state, as Deploy Previews are not given permission by the backend systems. This is an issue we are seeking to resolve.*

### Accessibility (a11y)

This website *must* conform to [WCAG 2.1 AA standards](https://www.w3.org/TR/WCAG21/).

This means that we must be strict about HTML semantics and care for various methods of using the application, especially navigating by keyboard or screen reader.

There are a number of automated tools that can help developers catch errors early on. In particular, the [axe DevTools Chrome extension](https://chrome.google.com/webstore/detail/axe-devtools-web-accessib/lhdoppojpmngadmnindnejefpokejbdd) is quite good.

Storybook is configured to run similar tests automatically on components as you develop them. This is found in the Accessibility pane.

npm run plop will also add a default aXe test via Jest to each component.

npm run lint:scripts - The [lint plugin: jsx-a11y](https://www.npmjs.com/package/eslint-plugin-jsx-a11y) will run static evaluation of the JSX to flag a11y issues.

These testing tools are aids, but passing them 100% does not necessarily mean that we have implemented components that are entirely accessible. Only manual testing with screen readers can ensure we meet our criteria.

### Pull Requests

Your pull request’s title and description become a historical record of the changes made to the codebase, so it is important that they convey the intention behind changes and describe the rationale behind them.

#### Pull Request labels

We have track labels and status labels.

When you open a PR, add:

- a track label. These are prefixed with -
- feature or bugfix if relevant
- ready for review if it's ready
- don't merge if it is a draft that isn't ready to be merged

After a reviewer checks out a PR, if changes are requested, they will remove ready for review and add changes requested. Once the PR is ready for review again, please remove changes requested and re-add ready for review.

#### Branch naming conventions

Our main branch is main. This branch is protected and must have a PR opened to have code integrated.

All PRs should be issued into main (unless being issued into another open PR). Our goal is to have consistent, fairly small, frequent merges.

Branch names should be prefixed with the track name, then the type of commit, then a description.

Examples:

- cart/fix/overflow-issue-on-cards
- browse/feature/integrate-algolia-into-discovery-pages
- fulfillment/feature/add-to-order-dialog

#### Pull Request title conventions

##### Examples

Let's start off with examples.

These all follow conventions:

- [#505975] Add ListItemReplacementDialog
- [#540789] RecipeServingDropdown | Prevent scroll on iOS when menu is open
- [#505983, #516082] List building | Add ability to edit My Items
- Saved List | Fix breadcrumbs, hide promotions, and fix print styles *(assuming there were no related tickets)*
- Bump express from 4.19.1 to 4.19.2

These break our conventions:

- ListItemReplacementDialog *(no verb)*
- [540789] RecipeServingDropdown | Prevent scroll on iOS when menu is open *(improper numbering)*
- [#516082, #505983] List building | Add ability to edit My Items *(unsorted numbering)*
- [#505983] [#516082] List building | Add ability to edit My Items *(multiple brackets for numbering)*
- Saved List | Fixing breadcrumbs, hid promotions, and fixed print styles *(doesn’t use imperative mood)*
- Fix breadcrumbs, hide promotions, and fix print styles *(provides no context. Where in the app is affected?)*
- Saved List | Fixes *(too vague)*

##### PR titles *must* have at least one *verb* that uses *imperative mood*

Verbs are important! Does it fix something, add something, remove something?

Imperative mood sounds like a command.

These are imperative. Use these:

- Add
- Fix
- Integrate
- Refactor
- Update
- Remove

These are not. *Do not* use these:

- Adds, Added, Adding
- Fixes, Fixed, Fixing
- Integrates, Integrated, Integrating
- Refactors, Refactored, Refactoring
- Updates, Updated, Updating
- Removes, Removed, Removing

##### PR titles *should* have ticket numbers (if an ADO ticket is related)

Include ADO ticket numbers within square brackets, separated by commas, in numerical order, with # signs. If there is no ticket, omit the brackets.

Do: [#500170, #500455]

Don’t: [263748] [117263]

Don’t: []

##### PR titles *may* add context (what is being affected or where in the app) followed by a pipe

It can be challenging to write in imperative mood while also providing context as to what is being changed. We can prefix the context beforehand and separate it with a pipe.

Ex. [#500170] Cart | Integrate new APIs for Instacart

The alternative is to add the context at the end of the line:

Ex. [#500170] Integrate new APIs for Instacart into Cart

Both are OK. The first is probably more clear because the context (what is being affected) comes first.

#### Pull Request description conventions

A template is added to kickstart a good description for your PR. Please remove any parts of the template that aren't necessary to convey your intention—we don't need leftover headings in our history.

Under ## Description, we want a broad overview of the changes. In some cases, if the changes are succinct enough, the title of the PR may suffice.

### Components Added is a decent section to provide a list of new components the PR adds, with a brief description of what each component handles

### Files changed is a good place to note changes to utility files or shared files, and for what reason they were updated. You don’t have to write an extensive list of every file updated here.

## Tickets is an important section. Please drop a link to the ADO ticket (or tickets) that should be sent to QA when the PR is merged. If this PR is a partial, please note that the PR does not fully complete a ticket, so that reviewers know not to reassign it once merged.

In your PR add the following syntax:
<br>AB#ticket-number
<br>Replace ticket-number with the actual ADO work item ID (e.g., AB#576596). "AB" for Azure Board. Once you save the PR, the system will automatically generate a link between the PR and the ADO story's "Development" section.

## Figma contains link to the associated Figma files. Don’t go out of your way to find these, but if they are present in the ADO ticket, please link them here too.

## More information/screenshots is a very helpful section to populate. As of March 2024, many of our deploy previews do not have safelisted APIs, so the only way a reviewer can get a glimpse of what you’ve created at a glance is via a screen shot you attach. This can help us catch UI inconsistencies early and also provide easy context of what the code being reviewed is meant to create, which can help reviewers identify accessibility requirements that may otherwise not be obvious by looking at code exclusively.

### CSP Headers

Definition:

The HTTP Content-Security-Policy response header allows website administrators to control resources the user agent is allowed to load for a given page. With a few exceptions, policies mostly involve specifying server origins and script endpoints. This helps guard against cross-site scripting attacks ([Cross-site_scripting](https://developer.mozilla.org/en-US/docs/Glossary/Cross-site_scripting)).For more information, see the introductory article on [Content Security Policy (CSP)](https://developer.mozilla.org/en-US/docs/Web/HTTP/CSP).

#### Updating Configuration

CSP Headers and functionality are stored in 2 files:

- ./middleware.ts - This file should remain untouched if needing to update the CSP configuration.
- ./config/csp/index.tsx - This file is were you can update the CSP configuration.

#### CSP Modes

For this application there are 3 modes concerning CSP and how the site treats them. These modes are controlled by a by a value that is set in the different environment files. The name of the environment variable is CSP_MODE and the values and descriptions are as follows:

- 'off' - This value will still run your request through middleware, but will not add any headers. You should see no difference in the in the results your code when using this value. This is a non-breaking value.
- 'report-only' - This value will evaluate the CSP headers that are specified in your configuration, and print the results in the browser developer console. Though this value is non-breaking from a code perspective, it can cause significant performance issue on a page depending on the site configuration and app code.
- 'enforce' - This value will actively block domains that are not included in in the site CSP configuration. This can be a breaking value depending on the current accuracy of the CSP configuration or if new functionality that reaches cross site has been introduced.
