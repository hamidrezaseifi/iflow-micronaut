import { userService } from '../services/user.service';
import router from '../router'

const user = JSON.parse(sessionStorage .getItem('user'));
const initialState = user
    ? { status: { loggedIn: true }, user }
    : { status: {}, user: null };

export const authentication = {
    namespaced: true,
    state: initialState,
    actions: {
        login({ dispatch, commit }, { username, password }) {

            commit('loginRequest', { username });

            userService.login(username, password)
                .then(
                    user => {
                        commit('loginSuccess', user);
                        router.push('/');
                    },
                    error => {
                        commit('loginFailure', error);
                        dispatch('alert/error', error, { root: true });
                    }
                );
        },
        logout({ commit }) {
            userService.logout();
            commit('logout');
            router.push('/login');
        }
    },
    mutations: {
        loginRequest(state, user) {
            state.status = { loggingIn: true };
            state.user = user;
            state.message = false;
        },
        loginSuccess(state, user) {
            state.status = { loggedIn: true };
            state.user = user;
            state.message = false;
        },
        loginFailure(state, error) {
            console.log('loginFailure', error);
            state.status = {loggedIn: false};
            state.user = null;
            state.message = error;
        },
        logout(state) {
            state.status = {loggedIn: false};
            state.user = null;
            state.message = false;
        }
    }
}